import SwiftUI
import shared

@main
struct iOSApp: App {
    
    
    init() {
        AppKt.startApp()
    }
    
	var body: some Scene {
		WindowGroup {
            ContentView()
		}
	}
}
