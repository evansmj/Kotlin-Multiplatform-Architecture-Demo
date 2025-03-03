import SwiftUICore

struct PortfolioCard: View {
    let bitcoinPrice: String
    let bitcoin24HChange: String
    let bitcoinHoldingsBtc: String
    
    var body: some View {
        VStack(alignment: .center, spacing: 8) {
            Text("Portfolio")
                .font(.headline)
            
            Text(bitcoinHoldingsBtc)
                .font(.subheadline)
            
            Text(bitcoinPrice)
                .font(.subheadline)
            
            Text(bitcoin24HChange)
                .font(.subheadline)
        }
        .frame(maxWidth: .infinity, minHeight: 100, alignment: .leading)
        .padding()
        .background(Color.white)
        .cornerRadius(16)
        .shadow(color: Color.black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}
