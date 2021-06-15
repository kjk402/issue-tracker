//
//  DataTransferService.swift
//  IssueTrackerApp
//
//  Created by Song on 2021/06/15.
//

import Foundation

public enum DataTransferError: Error {
    case noResponse
    case parsing(Error)
    case networkFailure(NetworkError)
}

public final class DataTransferService {
    typealias CompletionHandler<T> = (Result<T, DataTransferError>) -> Void
    private let networkService = NetworkService()

    @discardableResult
    func request<T: Decodable>(with endpoint: Endpoint,
                               completion: @escaping CompletionHandler<T>) -> URLSessionDataTask? {
        return networkService.request(endpoint: endpoint) { result in
            switch result {
            case .success(let data):
                let result: Result<T, DataTransferError> = self.decode(data: data)
                DispatchQueue.main.async {
                    return completion(result)
                }
            case .failure(let error):
                let error = self.resolve(networkError: error)
                DispatchQueue.main.async {
                    return completion(.failure(error))
                }
            }
        }
    }

    private func decode<T: Decodable>(data: Data?) -> Result<T, DataTransferError> {
        do {
            guard let data = data else { return .failure(.noResponse) }
            let result: T = try JSONDecoder().decode(T.self, from: data)
            return .success(result)
        } catch {
            return .failure(.parsing(error))
        }
    }

    private func resolve(networkError error: NetworkError) -> DataTransferError {
        return .networkFailure(error)
    }
}
