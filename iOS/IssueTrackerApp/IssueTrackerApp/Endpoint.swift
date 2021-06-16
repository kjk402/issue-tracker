//
//  Endpoint.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/12.
//

import Foundation

enum HTTPMethodType: String {
    case get = "GET"
    case post = "POST"
}

enum BaseURL: String {
    case api = "http://ec2-13-124-158-166.ap-northeast-2.compute.amazonaws.com/"
    case gitHub = "https://github.com/"
}

enum RequestGenerationError: Error {
    case components
}

class Endpoint {
    private let baseURL: BaseURL?
    private let path: String
    private let method: HTTPMethodType
    private let headerParamaters: [String: String]
    private let queryParameters: [String: Any]
    let responseDecoder: ResponseDecoder

    init(baseURL: BaseURL? = nil,
         path: String,
         method: HTTPMethodType,
         headerParamaters: [String: String] = [:],
         queryParameters: [String: Any] = [:],
         responseDecoder: ResponseDecoder = JSONResponseDecoder()) {
        self.baseURL = baseURL
        self.path = path
        self.method = method
        self.headerParamaters = headerParamaters
        self.queryParameters = queryParameters
        self.responseDecoder = responseDecoder
    }

    func urlRequest() throws -> URLRequest {
        let url = try url()
        var urlRequest = URLRequest(url: url)
        urlRequest.httpMethod = method.rawValue
        urlRequest.allHTTPHeaderFields = headerParamaters
        return urlRequest
    }

    func url() throws -> URL {
        let endpoint: String
        if let baseURL = baseURL {
            endpoint = "\(baseURL.rawValue)\(path)"
        } else {
            endpoint = path
        }

        guard var urlComponents = URLComponents(string: endpoint) else {
            throw RequestGenerationError.components
        }
        var urlQueryItems = [URLQueryItem]()
        queryParameters.forEach {
            urlQueryItems.append(URLQueryItem(name: $0.key, value: "\($0.value)"))
        }
        urlComponents.queryItems = !urlQueryItems.isEmpty ? urlQueryItems : nil
        guard let url = urlComponents.url else { throw RequestGenerationError.components }
        return url
    }
}
