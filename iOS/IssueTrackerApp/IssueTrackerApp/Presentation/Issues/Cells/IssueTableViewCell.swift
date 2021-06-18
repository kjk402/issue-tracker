//
//  IssueTableViewCell.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/10.
//

import UIKit

class IssueTableViewCell: UITableViewCell {
    static let reuseIdentifier = String(describing: IssueTableViewCell.self)
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var milestoneLabel: UILabel!
    @IBOutlet weak var labelBackgroundView: UIView!
    @IBOutlet weak var labelLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        configureLabelBackgroundView()
    }

    static func nib() -> UINib {
        return UINib(nibName: reuseIdentifier, bundle: nil)
    }

    private func configureLabelBackgroundView() {
        labelBackgroundView.layer.masksToBounds = true
        labelBackgroundView.layer.cornerRadius = 14.0
    }

    func fill(with viewModel: IssueViewModel) {
        titleLabel.text = viewModel.title
        descriptionLabel.text = viewModel.description
        milestoneLabel.text = viewModel.milestone
        labelLabel.text = viewModel.label
    }
}
