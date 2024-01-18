import Foundation

@objc public class AndroidRestrictedInfo: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
