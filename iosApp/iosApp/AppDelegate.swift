import Foundation
import SwiftUI
import shared

class AppDelegate: NSObject, UIApplicationDelegate {
    let root: RootComponent = AppComponentKt.appComponent(componentContext: DIComponentContext_iosKt.DefaultDIComponentContext())
}
