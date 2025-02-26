import SwiftUI
import Shared

struct ContentView: View {
    @State var selection = 1
    
    var body: some View {
        NavigationStack {
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
            .toolbar {
                MainToolbar()
            }
            .padding()
        }
    }
}

struct MainToolbar: ToolbarContent {
    var body: some ToolbarContent {
        ToolbarItem(placement: .navigationBarLeading) {
            Text("Personal account")
        }
        ToolbarItem(placement: .topBarTrailing) {
            Button(action: {
                print("Notification bell tapped")
            }) {
                Image(systemName: "bell")
                    .foregroundColor(.primary)
            }
        }
        ToolbarItem(placement: .topBarTrailing) {
            Button(action: {
                print("Help tapped")
            }) {
                Image(systemName: "questionmark.circle")
                    .foregroundColor(.primary)
            }
        }
    }
}

#Preview {
    ContentView()
}
