//
//  IssuesViewController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/09.
//

import UIKit

class IssuesViewController: UIViewController {
    @IBOutlet weak var issuesTableView: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.title = "이슈"

        configureTableView()
    }

    private func configureTableView() {
        issuesTableView.register(IssueTableViewCell.nib(), forCellReuseIdentifier: IssueTableViewCell.reuseIdentifier)
        issuesTableView.estimatedRowHeight = UITableView.automaticDimension
        issuesTableView.dataSource = self
    }
}

extension IssuesViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 0
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: IssueTableViewCell.reuseIdentifier,
                                                       for: indexPath) as? IssueTableViewCell else {
            return UITableViewCell()
        }
        return cell
    }
}
