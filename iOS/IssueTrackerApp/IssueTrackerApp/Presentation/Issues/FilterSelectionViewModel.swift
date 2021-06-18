//
//  FilterSelectionViewModel.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import Foundation

final class FilterSelectionViewModel {
    let screenTitle: String = "필터"
    var sections: [SectionViewModel]

    init(sections: [SectionViewModel]) {
        self.sections = sections
    }

    func didSelectItem(at indexPath: IndexPath) {
        sections[indexPath.section].filters[indexPath.row].toggle()
    }
}

struct SectionViewModel {
    let headerTitle: String
    var filters: [FilterViewModel]
}

struct FilterViewModel {
    let name: String
    var isSelected: Bool

    mutating func toggle() {
        isSelected = !isSelected
    }
}
