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
    enum Constant {
        static let scheme = "issuetracker"
        static let landingTabBarID = "LandingTabBarController"
        static let buttonCornerRadius: CGFloat = 20.0
        static let codeParameterKey = "code"
    }
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
        viewModel.$user
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
                                                 callbackURLScheme: Constant.scheme) { callbackURL, error in
            guard error == nil, let callbackURL = callbackURL else { return }
            let queryItems = URLComponents(string: callbackURL.absoluteString)?.queryItems
            guard let code = queryItems?.filter({ $0.name == Constant.codeParameterKey }).first?.value else { return }
            completion(code)
        }
        session.presentationContextProvider = self
        session.prefersEphemeralWebBrowserSession = true
        session.start()
    }

    private func goToLandingPage(with userProfile: User) {
        guard let landingTabBarController = storyboard?
                .instantiateViewController(identifier: Constant.landingTabBarID) as? LandingTabBarController else {
            return
        }
        landingTabBarController.user = userProfile
        landingTabBarController.modalPresentationStyle = .fullScreen
        self.present(landingTabBarController, animated: true, completion: nil)
    }

    private func configureGitHubLoginButton() {
        gitHubLoginButton.layer.masksToBounds = false
        gitHubLoginButton.layer.cornerRadius = Constant.buttonCornerRadius
        gitHubLoginButton.imageView?.contentMode = .scaleAspectFit
    }

    private func configureAppleLoginButton() {
        appleLoginButton.layer.masksToBounds = false
        appleLoginButton.layer.cornerRadius = Constant.buttonCornerRadius
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
