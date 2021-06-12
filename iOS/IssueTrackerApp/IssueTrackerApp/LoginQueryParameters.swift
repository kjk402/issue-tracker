//
//  LoginQueryParameters.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/12.
//

import Foundation

struct LoginQueryParameters: Decodable {
    let clientId: String
    let scope: String
}
