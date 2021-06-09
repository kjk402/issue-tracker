//
//  ViewController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/08.
//

import UIKit

class LoginViewController: UIViewController {
    @IBOutlet weak var gitHubLoginButton: UIButton!
    @IBOutlet weak var appleLoginButton: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()

        configureGitHubLoginButton()
        configureAppleLoginButton()
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
}
