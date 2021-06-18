//
//  FilterSelectionTableViewController.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import UIKit

class FilterSelectionTableViewController: UITableViewController {
    private var viewModel: FilterSelectionViewModel!

    override func viewDidLoad() {
        super.viewDidLoad()
        viewModel = makeFilterSelectionViewModel()

        navigationItem.title = viewModel.screenTitle
        configureCancelBarButtonItem()
        configureSaveBarButtonItem()
    }

    private func makeFilterSelectionViewModel() -> FilterSelectionViewModel {
        return FilterSelectionViewModel(sections: [
            makeSectionViewModel(headerTitle: "상태", filters: [
                makeFilterViewModel(name: "열린 이슈", isSelected: true),
                makeFilterViewModel(name: "내가 작성한 이슈", isSelected: false),
                makeFilterViewModel(name: "나에게 할당된 이슈", isSelected: false),
                makeFilterViewModel(name: "내가 댓글을 남긴 이슈", isSelected: false),
                makeFilterViewModel(name: "닫힌 이슈", isSelected: false)
            ]),
            makeSectionViewModel(headerTitle: "작성자", filters: [
                makeFilterViewModel(name: "JK", isSelected: false),
                makeFilterViewModel(name: "Honux", isSelected: false)
            ]),
            makeSectionViewModel(headerTitle: "레이블", filters: [
                makeFilterViewModel(name: "레이블 없음", isSelected: false),
                makeFilterViewModel(name: "Documentation", isSelected: false),
                makeFilterViewModel(name: "bug", isSelected: false)
            ]),
            makeSectionViewModel(headerTitle: "마일스톤", filters: [
                makeFilterViewModel(name: "마일스톤 없음", isSelected: false),
                makeFilterViewModel(name: "마스터즈 코스", isSelected: false)
            ])
        ])
    }

    private func makeSectionViewModel(headerTitle: String,
                                      filters: [FilterViewModel]) -> SectionViewModel {
        return SectionViewModel(headerTitle: headerTitle, filters: filters)
    }

    private func makeFilterViewModel(name: String, isSelected: Bool) -> FilterViewModel {
        return FilterViewModel(name: name, isSelected: isSelected)
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
        return viewModel.sections.count
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.sections[section].filters.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let filterViewModel = viewModel.sections[indexPath.section].filters[indexPath.row]
        return fillCell(with: filterViewModel)
    }

    private func fillCell(with viewModel: FilterViewModel) -> UITableViewCell {
        let cell = UITableViewCell(style: .default, reuseIdentifier: "filterCell")
        cell.textLabel?.text = viewModel.name
        cell.textLabel?.font = viewModel.isSelected ? UIFont.systemFont(ofSize: 17.0, weight: .semibold)
            : UIFont.systemFont(ofSize: 17.0)
        cell.accessoryType = viewModel.isSelected ? .checkmark : .none
        cell.tintColor = #colorLiteral(red: 0.2039215686, green: 0.7803921569, blue: 0.3490196078, alpha: 1)
        cell.selectionStyle = .none
        return cell
    }

    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return viewModel.sections[section].headerTitle
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        viewModel.didSelectItem(at: indexPath)
        tableView.reloadRows(at: [indexPath], with: .none)
        tableView.deselectRow(at: indexPath, animated: true)
    }
}
