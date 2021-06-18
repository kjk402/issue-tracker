//
//  Issue.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import Foundation

struct Issue: Decodable {
    let title: String?
    let description: String?
    let milestone: String?
    let label: String?
}
