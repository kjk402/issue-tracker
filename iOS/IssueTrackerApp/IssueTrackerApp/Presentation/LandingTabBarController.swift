//
//  LandingTabBarController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import UIKit

class LandingTabBarController: UITabBarController {
    var user: User?
    private let dataTransferService = DataTransferService()

    override func viewDidLoad() {
        super.viewDidLoad()
        updateProfileImage()
    }

    private func updateProfileImage() {
        guard let profileImageURL = user?.profileImage else { return }
        let endpoint = Endpoint(path: profileImageURL, method: .get, responseDecoder: RawDataResponseDecoder())
        let userTabBarItem = self.tabBar.items?.last
        dataTransferService.request(with: endpoint) { (result: Result<Data, DataTransferError>) in
            let result = result.mapError { $0 as Error }
            switch result {
            case .success(let data):
                DispatchQueue.main.async {
                    userTabBarItem?.image = self.getCropedImage(data: data, size: CGSize(width: 25.0, height: 25.0))
                }
            case .failure(let error):
                print(error.localizedDescription)
                userTabBarItem?.image = UIImage(systemName: "person")
            }
        }
    }

    private func getCropedImage(data: Data, size: CGSize) -> UIImage? {
        return UIImage(data: data)?
            .cropIntoCircle(size: size)?
            .withRenderingMode(.alwaysOriginal)
    }
}
