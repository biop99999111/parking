@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/discount")
    public ResponseEntity<String> applyDiscount(
            @RequestParam Long storeId,
            @RequestParam String carNo,
            @RequestParam int discountMinutes) {

        storeService.applyStoreDiscount(storeId, carNo, discountMinutes);
        return ResponseEntity.ok("할인 적용 완료");
    }
}
