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
    private let actions: LoginViewModelActions?
    private let dataTransferService = DataTransferService()
    @Published var jwt: String = ""

    init(actions: LoginViewModelActions? = nil) {
        self.actions = actions
    }

    func requestLoginQueryParameters() {
        let endpoint = Endpoint(baseURL: .api,
                                path: "api/users/github",
                                method: .get,
                                headerParamaters: ["User-Agent": "IssueTrackeriOSApp"])
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
        let parameters = ["client_id": clientID, "scope": scope]
        let endpoint = Endpoint(baseURL: .gitHub,
                                path: "login/oauth/authorize",
                                method: .get,
                                queryParameters: parameters)
        guard let authURL = try? endpoint.url() else { return }
        self.actions?.configureSession(authURL) { code in
            self.requestJWT(with: code)
        }
    }

    func requestJWT(with code: String) {
        let parameters = ["code": code]
        let endpoint = Endpoint(baseURL: .api,
                                path: "api/users/github/callback",
                                method: .get,
                                headerParamaters: ["User-Agent": "IssueTrackeriOSApp"],
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
