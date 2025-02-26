import SwiftUI
import KMPObservableViewModelSwiftUI
import KMPNativeCoroutinesAsync
import Shared

struct PortfolioView: View {
    @StateViewModel var portfolioViewModel = PortfolioViewModel()
    @State private var statsState: StatsState? = nil
    
    var body: some View {
        VStack {
            switch (statsState) {
            case is StatsState.Loading:
                ProgressView()
                
            case let successState as StatsState.Success:
                if let bitcoinData = successState.stats.data["1"] {
                    Text("name = \(bitcoinData.name)")
                }
            case let errorState as StatsState.Error:
                if let cachedBitcoinData = errorState.cachedStats?.data["1"] {
                    Text("cached data = \(cachedBitcoinData.name)")
                } else {
                    Text("Error and no data found")
                }
            case is StatsState.None:
                Text("none")
            default:
                EmptyView()
            }
            
        }
        .onAppear {
            Task {
                do {
                    try await asyncFunction(for: portfolioViewModel.fetchStats())
                } catch {
                    print("Error fetching stats \(error)")
                }
            }
            Task {
                do {
                    let sequence = asyncSequence(for: portfolioViewModel.statsStateFlow)
                    for try await state in sequence {
                        statsState = state
                    }
                }
            }
        }
    }
}
