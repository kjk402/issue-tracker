//
//  ViewController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/08.
//

import UIKit
import Combine
import AuthenticationServices

class LoginViewController: UIViewController {
    @IBOutlet weak var gitHubLoginButton: UIButton!
    @IBOutlet weak var appleLoginButton: UIButton!
    private var viewModel: LoginViewModel!
    private var cancellables: Set<AnyCancellable> = []

    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel = makeLoginViewModel()
        bindViewModel()

        configureGitHubLoginButton()
        configureAppleLoginButton()
    }

    private func bindViewModel() {
        viewModel.$jwt
            .dropFirst()
            .sink { [weak self] in
                print($0)
            }
            .store(in: &cancellables)
    }

    private func makeLoginViewModel() -> LoginViewModel {
        let actions = LoginViewModelActions(configureSession: configureSession, goToLandingPage: goToLandingPage)
        return LoginViewModel(actions: actions)
    }

    private func configureSession(authURL: URL, completion: @escaping (String) -> Void) {
        let session = ASWebAuthenticationSession(url: authURL,
                                                 callbackURLScheme: "issuetracker") { callbackURL, error in
            guard error == nil, let callbackURL = callbackURL else { return }
            let queryItems = URLComponents(string: callbackURL.absoluteString)?.queryItems
            guard let code = queryItems?.filter({ $0.name == "code" }).first?.value else { return }
            completion(code)
        }
        session.presentationContextProvider = self
        session.prefersEphemeralWebBrowserSession = true
        session.start()
    }

    private func goToLandingPage() {
        guard let tabBarController = storyboard?.instantiateViewController(identifier: "LandingTabBarController") else {
            return
        }
        tabBarController.modalPresentationStyle = .fullScreen
        self.present(tabBarController, animated: true, completion: nil)
    }

    private func configureGitHubLoginButton() {
        gitHubLoginButton.layer.masksToBounds = false
        gitHubLoginButton.layer.cornerRadius = 20.0
        gitHubLoginButton.imageView?.contentMode = .scaleAspectFit
    }

    private func configureAppleLoginButton() {
        appleLoginButton.layer.masksToBounds = false
        appleLoginButton.layer.cornerRadius = 20.0
    }

    @IBAction func gitHubLoginButtonPressed(_ sender: UIButton) {
        viewModel.requestLoginQueryParameters()
    }
}

extension LoginViewController: ASWebAuthenticationPresentationContextProviding {
    func presentationAnchor(for session: ASWebAuthenticationSession) -> ASPresentationAnchor {
        return view.window ?? ASPresentationAnchor()
    }
}
