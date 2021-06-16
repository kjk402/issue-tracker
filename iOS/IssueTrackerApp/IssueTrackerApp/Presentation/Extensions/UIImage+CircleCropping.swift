//
//  UIImage+CircleCropping.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/16.
//

import UIKit

extension UIImage {
    func cropIntoCircle(size: CGSize) -> UIImage? {
        guard let cgImage = cgImage else { return nil }
        return UIGraphicsImageRenderer(size: size).image { _ in
            UIBezierPath(ovalIn: CGRect(origin: .zero, size: size)).addClip()
            UIImage(cgImage: cgImage, scale: scale, orientation: imageOrientation)
                .draw(in: CGRect(origin: .zero, size: size))
        }
    }
}
