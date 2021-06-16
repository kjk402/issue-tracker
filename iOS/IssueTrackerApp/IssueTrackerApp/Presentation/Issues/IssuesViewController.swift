//
//  IssuesViewController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/09.
//

import UIKit
import Combine

class IssuesViewController: UIViewController {
    @IBOutlet weak var issuesTableView: UITableView!
    private var searchController: UISearchController!
    private var viewModel: IssuesViewModel!
    private var cancellables: Set<AnyCancellable> = []

    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel = makeIssuesViewModel()
        bindViewModel()
        viewModel.load()

        navigationItem.title = viewModel.screenTitle
        configureFilterBarButtonItem()
        configureSelectBarButtonItem()
        configureTableView()
    }

    private func bindViewModel() {
        viewModel.$issues
            .dropFirst()
            .sink { [weak self] _ in
                self?.issuesTableView.reloadData()
            }
            .store(in: &cancellables)
    }

    private func makeIssuesViewModel() -> IssuesViewModel {
        return IssuesViewModel()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        configureSearchController()
    }

    private func configureFilterBarButtonItem() {
        let button = UIButton(type: .system)
        let filterImage = UIImage(systemName: "line.horizontal.3.decrease")
        button.setImage(filterImage, for: .normal)
        button.setTitle("필터", for: .normal)
        button.sizeToFit()
        button.imageView?.contentMode = .scaleAspectFit
        button.contentEdgeInsets = UIEdgeInsets(top: 13.0, left: 0.0, bottom: 13.0, right: 0.0)
        button.addTarget(self, action: #selector(showFilters), for: .touchUpInside)

        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: button)
    }

    @objc
    func showFilters() {
        let filterVC = FilterSelectionTableViewController(style: .grouped)
        let navigationController = UINavigationController(rootViewController: filterVC)
        navigationController.modalPresentationStyle = .automatic
        present(navigationController, animated: true)
    }

    private func configureSelectBarButtonItem() {
        let button = UIButton(type: .system)
        let checkmarkImage = UIImage(systemName: "checkmark.circle")
        button.setImage(checkmarkImage, for: .normal)
        button.setTitle("선택", for: .normal)
        button.sizeToFit()
        button.imageView?.contentMode = .scaleAspectFit
        button.contentEdgeInsets = UIEdgeInsets(top: 13.0, left: 0.0, bottom: 13.0, right: 0.0)
        button.semanticContentAttribute = .forceRightToLeft

        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: button)
    }

    private func configureTableView() {
        issuesTableView.register(IssueTableViewCell.nib(), forCellReuseIdentifier: IssueTableViewCell.reuseIdentifier)
        issuesTableView.estimatedRowHeight = UITableView.automaticDimension
        issuesTableView.dataSource = self
        issuesTableView.delegate = self
        let footerView = IssueTableViewFooterView(frame: CGRect(x: 0.0,
                                                                y: 0.0,
                                                                width: self.view.bounds.size.width,
                                                                height: 39.0))
        issuesTableView.tableFooterView = footerView
    }

    private func configureSearchController() {
        searchController = UISearchController(searchResultsController: nil)
        navigationItem.hidesSearchBarWhenScrolling = true
        navigationItem.searchController = searchController
        definesPresentationContext = true
    }
}

// MARK: - UITableViewDataSource, UITableViewDelegate

extension IssuesViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.issues.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: IssueTableViewCell.reuseIdentifier,
                                                       for: indexPath) as? IssueTableViewCell else {
            return UITableViewCell()
        }
        cell.fill(with: viewModel.issues[indexPath.row])
        return cell
    }

    func tableView(_ tableView: UITableView,
                   trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        let deleteContextualAction = configureDeleteAction()
        let closeContextualAction = configureCloseAction()
        let configuration = UISwipeActionsConfiguration(actions: [closeContextualAction, deleteContextualAction])
        configuration.performsFirstActionWithFullSwipe = false
        return configuration
    }

    private func configureDeleteAction() -> UIContextualAction {
        let action = UIContextualAction(style: .normal, title: "삭제") { [weak self] _, _, completionHandler in
            guard let self = self else { return }
            let alert = self.configureAlert()
            self.present(alert, animated: true, completion: nil)
            completionHandler(true)
        }
        action.backgroundColor = #colorLiteral(red: 1, green: 0.231372549, blue: 0.1882352941, alpha: 1)
        action.image = UIImage(systemName: "trash")
        return action
    }

    private func configureCloseAction() -> UIContextualAction {
        let action = UIContextualAction(style: .normal, title: "닫기") { _, _, completionHandler in
            completionHandler(true)
        }
        action.backgroundColor = #colorLiteral(red: 0.8, green: 0.831372549, blue: 1, alpha: 1)
        action.image = UIImage(systemName: "archivebox")
        return action
    }

    private func configureAlert() -> UIAlertController {
        let alert = UIAlertController(title: "정말로 이 이슈를 삭제하시겠습니까?",
                                      message: "삭제된 이슈는 복구할 수 없습니다.",
                                      preferredStyle: .alert)
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel, handler: nil)
        let deleteAction = UIAlertAction(title: "Delete", style: .destructive, handler: nil)
        alert.addAction(cancelAction)
        alert.addAction(deleteAction)
        return alert
    }

    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        issuesTableView.tableFooterView?.isHidden = searchController.searchBar.frame.size.height > 0
    }
}
