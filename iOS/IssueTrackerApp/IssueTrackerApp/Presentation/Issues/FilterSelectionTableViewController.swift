//
//  FilterSelectionTableViewController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import UIKit

class FilterSelectionTableViewController: UITableViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "필터"

        configureCancelBarButtonItem()
        configureSaveBarButtonItem()
    }

    private func configureCancelBarButtonItem() {
        let button = UIButton(type: .system)
        let image = UIImage(systemName: "chevron.backward")
        button.setImage(image, for: .normal)
        button.setTitle("취소", for: .normal)
        button.sizeToFit()
        button.imageView?.contentMode = .scaleAspectFit
        button.addTarget(self, action: #selector(cancel), for: .touchUpInside)

        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: button)
    }

    @objc
    func cancel() {
        dismiss(animated: true, completion: nil)
    }

    private func configureSaveBarButtonItem() {
        let button = UIButton(type: .system)
        button.setTitle("저장", for: .normal)
        button.titleLabel?.font = UIFont.systemFont(ofSize: 15.0, weight: .semibold)
        button.sizeToFit()
        button.imageView?.contentMode = .scaleAspectFit

        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: button)
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 0
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }
}
