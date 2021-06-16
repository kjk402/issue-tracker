//
//  UserProfile.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/15.
//

import Foundation

struct User: Decodable {
    let id: Int
    let nickname: String
    let name: String
    let profileImage: String
    let accessToken: String
}
