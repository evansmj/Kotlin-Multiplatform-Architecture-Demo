import SwiftUI
import KMPObservableViewModelSwiftUI
import KMPNativeCoroutinesAsync
import Shared

struct PortfolioView: View {
    @StateViewModel var portfolioViewModel = PortfolioViewModel()
    @State private var isLoading: Bool = false
    @State private var error: String? = nil
    @State private var portfolioItemList: [PortfolioItem] = []

    var body: some View {
        ZStack {
            Color(UIColor(red: 0x00 / 255.0, green: 0x28 / 255.0, blue: 0x51 / 255.0, alpha: 1.0))
            
            ScrollView {
                VStack(alignment: .leading, spacing: 8) {
                    
                    ForEach(portfolioItemList, id: \.id) { item in
                        if let portfolioItem = item as? PortfolioItemPortfolioCardData {
                            PortfolioCard(
                                bitcoinPrice: portfolioItem.bitcoinPrice,
                                bitcoin24HChange: portfolioItem.bitcoin24HChange,
                                bitcoinHoldingsBtc: portfolioItem.bitcoinHoldingsBtc
                            )
                        } else if let cashItem = item as? PortfolioItemCashCardData {
                            CashCard(dollarBalance: cashItem.dollarBalance)
                        } else if let vaultItem = item as? PortfolioItemVaultCardData {
                            VaultCard()
                        }
                    }
                }
                .padding(16)
            }
            
            if (isLoading) {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: .white))
                    .scaleEffect(1.5)
            }
            
            if let errorMessage = error {
                Text("Error message: \(errorMessage)")
                    .foregroundColor(.red)
                    .padding()
            }
        }
        .onAppear {
            Task {
                do {
                    try await asyncFunction(for: portfolioViewModel.fetchBitcoinStats())
                } catch {
                    print("Error fetching bitcoin stats \(error)")
                }
            }
            Task {
                do {
                    try await asyncFunction(for: portfolioViewModel.fetchUserData())
                } catch {
                    print("Error fetching user data")
                }
            }
            Task {
                do {
                    let sequence = asyncSequence(for: portfolioViewModel.isLoadingFlow)
                    for try await item in sequence {
                        isLoading = item.boolValue
                    }
                }
            }
            Task {
                do {
                    let sequence = asyncSequence(for: portfolioViewModel.errorFlow)
                    for try await item in sequence {
                        error = item
                    }
                }
            }
            Task {
                do {
                    let sequence = asyncSequence(for: portfolioViewModel.portfolioItemsListFlow)
                    for try await list in sequence {
                        portfolioItemList = list
                    }
                }
            }
        }
    }
}
