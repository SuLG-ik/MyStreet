import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    
    private let root: RootAppRoot
    
    
    init(_ root: RootAppRoot) {
        self.root = root
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(root: root)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self)
    var appDelegate: AppDelegate
    
    var body: some View {
        ComposeView(appDelegate.root)
            .ignoresSafeArea(.all) // Compose has own keyboard handler
    }
}


