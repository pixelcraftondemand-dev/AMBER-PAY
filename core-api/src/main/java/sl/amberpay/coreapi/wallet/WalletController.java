package sl.amberpay.coreapi.wallet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sl.amberpay.coreapi.ledger.LedgerWalletService;

@RestController
@RequestMapping("/v1")
public class WalletController {

    private final LedgerWalletService ledgerWalletService;

    public WalletController(LedgerWalletService ledgerWalletService) {
        this.ledgerWalletService = ledgerWalletService;
    }

    @GetMapping("/wallets")
    public ResponseEntity<Map<String, Object>> wallets() {
        List<Map<String, Object>> wallets = ledgerWalletService.wallets().stream()
            .map(wallet -> {
                Map<String, Object> walletMap = new java.util.HashMap<>();
                walletMap.put("id", wallet.id());
                walletMap.put("currency", wallet.currency());
                walletMap.put("available_minor", wallet.available_minor());
                walletMap.put("held_minor", wallet.held_minor());
                walletMap.put("total_minor", wallet.total_minor());
                walletMap.put("status", wallet.status());
                return walletMap;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(Map.of("wallets", wallets));
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<Map<String, Object>> wallet(@PathVariable String id) {
        LedgerWalletService.WalletRecord wallet = ledgerWalletService.getWallet(id);
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet not found.");
        }
        return ResponseEntity.ok(Map.of(
            "id", wallet.id(),
            "currency", wallet.currency(),
            "available_minor", wallet.available_minor(),
            "held_minor", wallet.held_minor(),
            "total_minor", wallet.total_minor(),
            "status", wallet.status()
        ));
    }

    @GetMapping("/wallets/{id}/transactions")
    public ResponseEntity<Map<String, Object>> walletTransactions(
        @PathVariable String id,
        @RequestParam(defaultValue = "25") int limit,
        @RequestParam(required = false) String cursor
    ) {
        LedgerWalletService.WalletRecord wallet = ledgerWalletService.getWallet(id);
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet not found.");
        }

        List<Map<String, Object>> entries = ledgerWalletService.transactionsForWallet(id).stream()
            .limit(Math.max(1, Math.min(limit, 100)))
            .map(entry -> {
                Map<String, Object> entryMap = new java.util.HashMap<>();
                entryMap.put("entry_id", entry.entry_id());
                entryMap.put("journal_id", entry.journal_id());
                entryMap.put("journal_type", entry.journal_type());
                entryMap.put("direction", entry.direction());
                entryMap.put("amount_minor", entry.amount_minor());
                entryMap.put("currency", entry.currency());
                entryMap.put("created_at", entry.created_at());
                return entryMap;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of(
            "entries", entries,
            "next_page_token", ""
        ));
    }
}
