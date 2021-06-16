//
//  IssueTableViewFooterView.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/10.
//

import UIKit

class IssueTableViewFooterView: UIView {
    private let helpTextLabel = UILabel()

    override init(frame: CGRect) {
        super.init(frame: frame)
        configureLabel()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureLabel()
    }

    private func configureLabel() {
        helpTextLabel.translatesAutoresizingMaskIntoConstraints = false
        helpTextLabel.font = UIFont.systemFont(ofSize: 13.0)
        helpTextLabel.textColor = #colorLiteral(red: 0.5294117647, green: 0.5294117647, blue: 0.5529411765, alpha: 1)
        helpTextLabel.text = "아래로 당기면 검색바가 보여요! 👀"
        self.addSubview(helpTextLabel)
        NSLayoutConstraint.activate([
            helpTextLabel.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            helpTextLabel.centerXAnchor.constraint(equalTo: self.centerXAnchor)
        ])
    }
}
