import SwiftUICore

struct PortfolioCard: View {
    let bitcoinPrice: Double
    let bitcoin24HChange: Double
    let bitcoinHoldingsBtc: Double
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("Portfolio")
                .font(.headline)
            
            Text("\(bitcoinHoldingsBtc, specifier: "%.8f") BTC")
                .font(.subheadline)
            
            Text("$\(bitcoinPrice, specifier: "%.2f")")
                .font(.subheadline)
            
            Text("\(bitcoin24HChange, specifier: "%.2f")%")
                .font(.subheadline)
        }
        .frame(maxWidth: .infinity, minHeight: 100, alignment: .leading)
        .padding()
        .background(Color.white)
        .cornerRadius(16)
        .shadow(color: Color.black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}
