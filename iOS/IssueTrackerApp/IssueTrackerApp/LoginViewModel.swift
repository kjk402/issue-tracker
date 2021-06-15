//
//  LoginViewModel.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/15.
//

import Foundation

struct LoginViewModelActions {
    let configureSession: (URL, @escaping (String) -> Void) -> Void
    let goToLandingPage: () -> Void
}

final class LoginViewModel {
    enum ParameterKey {
        static let userAgent = "User-Agent"
        static let clientID = "client_id"
        static let scope = "scope"
        static let code = "code"
    }
    enum ParameterValue {
        static let userAgent = "IssueTrackeriOSApp"
    }
    enum Path {
        static let loginQueryParametersRequest = "api/users/github"
        static let authCodeRequest = "login/oauth/authorize"
        static let jwtRequest = "api/users/github/callback"
    }
    private let actions: LoginViewModelActions?
    private let dataTransferService = DataTransferService()
    @Published var jwt: String = ""

    init(actions: LoginViewModelActions? = nil) {
        self.actions = actions
    }

    func requestLoginQueryParameters() {
        let endpoint = Endpoint(baseURL: .api,
                                path: Path.loginQueryParametersRequest,
                                method: .get,
                                headerParamaters: [ParameterKey.userAgent: ParameterValue.userAgent])
        dataTransferService.request(with: endpoint) { (result: Result<LoginQueryParameters, DataTransferError>) in
            switch result {
            case .success(let response):
                self.requestAuthorizationCode(clientID: response.clientId, scope: response.scope)
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }

    private func requestAuthorizationCode(clientID: String, scope: String) {
        let parameters = [ParameterKey.clientID: clientID, ParameterKey.scope: scope]
        let endpoint = Endpoint(baseURL: .gitHub,
                                path: Path.authCodeRequest,
                                method: .get,
                                queryParameters: parameters)
        guard let authURL = try? endpoint.url() else { return }
        self.actions?.configureSession(authURL) { code in
            self.requestJWT(with: code)
        }
    }

    func requestJWT(with code: String) {
        let parameters = [ParameterKey.code: code]
        let endpoint = Endpoint(baseURL: .api,
                                path: Path.jwtRequest,
                                method: .get,
                                headerParamaters: [ParameterKey.userAgent: ParameterValue.userAgent],
                                queryParameters: parameters)
        dataTransferService.request(with: endpoint) { (result: Result<JWT, DataTransferError>) in
            switch result {
            case .success(let response):
                self.jwt = response.accessToken
                self.actions?.goToLandingPage()
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
