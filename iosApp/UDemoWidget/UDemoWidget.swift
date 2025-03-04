import WidgetKit
import SwiftUI
import KMPNativeCoroutinesAsync
import Shared

struct Provider: TimelineProvider {
    
    init() {
        KoinHelper().doInitKoin()
    }
    
    func placeholder(in context: Context) -> PriceEntry {
        PriceEntry(date: Date(), price: "$0.00")
    }
    
    func getSnapshot(in context: Context, completion: @escaping (PriceEntry) -> ()) {
        let entry = PriceEntry(date: Date(), price: "$1.11")
        completion(entry)
    }
    
    func getTimeline(in context: Context, completion: @escaping (Timeline<Entry>) -> ()) {
        let fetchedStats = fetchBitcoinStats()
        let price = FormatUtilKt.formatDollars(amount: fetchedStats?.quote.usd.price ?? 0.0)
        
        var entries: [PriceEntry] = []
        
        // Generate a timeline consisting of five entries an hour apart, starting from the current date.
        let currentDate = Date()
        for hourOffset in 0 ..< 5 {
            let entryDate = Calendar.current.date(byAdding: .hour, value: hourOffset, to: currentDate)!
            let entry = PriceEntry(date: entryDate, price: "\(price)")
            entries.append(entry)
        }
        
        let timeline = Timeline(entries: entries, policy: .atEnd)
        completion(timeline)
    }
    
    /**
     Replace these calls with a call to the database.  It is best practice to get widget data
     from local storage.  Price data can be scheduled to be updated every once in a while.
     */
    func fetchBitcoinStats() -> BitcoinStatsData? {
        let semaphore = DispatchSemaphore(value: 0)
        var fetchedStats: BitcoinStatsData?
        
        Task {
            do {
                fetchedStats = try await asyncFunction(for: KoinHelper().fetchBitcoinStats(refresh: true))
                print("fetched stats = \(String(describing: fetchedStats))")
            } catch {
                print("error: \(error)")
            }
            semaphore.signal()
        }
        
        _ = semaphore.wait(timeout: .now() + 10)
        
        return fetchedStats
    }
    
    //    func relevances() async -> WidgetRelevances<Void> {
    //        // Generate a list containing the contexts this widget is relevant in.
    //    }
}

struct PriceEntry: TimelineEntry {
    let date: Date
    let price: String
}

struct UDemoWidgetEntryView : View {
    var entry: Provider.Entry
    
    var body: some View {
        VStack {
            Text("Price: \(entry.price)")
            Text(entry.date, style: .time)
        }
    }
}

struct UDemoWidget: Widget {
    let kind: String = "UDemoWidget"
    
    var body: some WidgetConfiguration {
        StaticConfiguration(kind: kind, provider: Provider()) { entry in
            if #available(iOS 17.0, *) {
                UDemoWidgetEntryView(entry: entry)
                    .containerBackground(.fill.tertiary, for: .widget)
            } else {
                UDemoWidgetEntryView(entry: entry)
                    .padding()
                    .background()
            }
        }
        .configurationDisplayName("My Widget")
        .description("This is an example widget.")
    }
}

#Preview(as: .systemSmall) {
    UDemoWidget()
} timeline: {
    PriceEntry(date: .now, price: "$1")
    PriceEntry(date: .now, price: "$100")
}
