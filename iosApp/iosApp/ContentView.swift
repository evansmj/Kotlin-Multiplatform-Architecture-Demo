import SwiftUI
import Shared

struct ContentView: View {
    @State var selection = 1
    
    var body: some View {
        TabView(selection: $selection) {
            Text("Buy")
                .tabItem {
                    Label("Buy", systemImage: "cart.fill")
                }.tag(0)
            PortfolioView()
                .tabItem {
                    Label("Portfolio", systemImage: "house.fill")
                }.tag(1)
            Text("Receive")
                .tabItem {
                    Label("Receive", systemImage: "plus.circle.fill")
                }.tag(2)
        }
    }
}

#Preview {
    ContentView()
}
