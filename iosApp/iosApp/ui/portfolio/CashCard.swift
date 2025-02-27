import SwiftUICore

struct CashCard: View {
    let dollarBalance: Double
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text("Cash:")
                .font(.headline)
            
            Text("$\(dollarBalance, specifier: "%.2f")")
                .font(.subheadline)
        }
        .padding()
        .frame(maxWidth: .infinity, minHeight: 100, alignment: .leading)
        .background(Color.white)
        .cornerRadius(16)
        .shadow(color: Color.black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}
