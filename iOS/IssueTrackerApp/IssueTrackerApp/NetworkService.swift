//
//  NetworkService.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/15.
//

import Foundation

public enum NetworkError: Error {
    case error(statusCode: Int, data: Data?)
    case notConnected
    case cancelled
    case generic(Error)
    case urlGeneration
}

public final class NetworkService {
    typealias CompletionHandler = (Result<Data?, NetworkError>) -> Void

    func request(endpoint: Endpoint, completion: @escaping CompletionHandler) -> URLSessionDataTask? {
        do {
            let urlRequest = try endpoint.urlRequest()
            return request(urlRequest, completion: completion)
        } catch {
            completion(.failure(.urlGeneration))
            return nil
        }
    }

    private func request(_ request: URLRequest, completion: @escaping CompletionHandler) -> URLSessionDataTask {
        let sessionDataTask = URLSession.shared.dataTask(with: request) { data, response, requestError in
            if let requestError = requestError {
                var error: NetworkError
                if let response = response as? HTTPURLResponse {
                    error = .error(statusCode: response.statusCode, data: data)
                } else {
                    error = self.resolve(error: requestError)
                }
                completion(.failure(error))
            } else {
                completion(.success(data))
            }
        }
        sessionDataTask.resume()
        return sessionDataTask
    }

    private func resolve(error: Error) -> NetworkError {
        let code = URLError.Code(rawValue: (error as NSError).code)
        switch code {
        case .notConnectedToInternet:
            return .notConnected
        case .cancelled:
            return .cancelled
        default:
            return .generic(error)
        }
    }
}
