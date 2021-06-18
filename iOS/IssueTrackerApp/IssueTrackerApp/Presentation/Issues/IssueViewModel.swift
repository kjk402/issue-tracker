//
//  IssueViewModel.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import Foundation

struct IssueViewModel {
    let title: String?
    let description: String?
    let milestone: String?
    let label: String?

    init(issue: Issue) {
        self.title = issue.title ?? ""
        self.description = issue.description ?? ""
        self.milestone = issue.milestone ?? ""
        self.label = issue.label ?? ""
    }
}
