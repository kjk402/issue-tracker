//
//  IssuesMockJSON.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import Foundation

struct MockJSON {
    static let issues = """
    [
        {
            "title": "제목",
            "description": "이슈에 대한 설명(최대 두 줄까지 보여줄 수 있다)",
            "milestone": "마일스톤 이름",
            "label": "레이블 이름"
        },
        {
            "title": "iOS 이슈트래커 개발",
            "description": "6월 7일 월요일 부터 6월 25일 금요일 까지",
            "milestone": "마스터즈 코스",
            "label": "documentation"
        }
    ]
    """.data(using: .utf8)!

}
