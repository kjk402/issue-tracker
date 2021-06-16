//
//  IssuesViewModel.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import Foundation

final class IssuesViewModel {
    let screenTitle: String = "이슈"
    let dataTransferService = DataTransferService()
    @Published var issues: [IssueViewModel] = []

    func load() {
        let mockData = MockJSON.issues
        let result: Result<[Issue], DataTransferError> = dataTransferService.decode(data: mockData,
                                                                                    decoder: JSONResponseDecoder())
        switch result {
        case .success(let items):
            self.issues = items.map(IssueViewModel.init)
        case .failure(let error):
            print(error.localizedDescription)
        }
    }
}
