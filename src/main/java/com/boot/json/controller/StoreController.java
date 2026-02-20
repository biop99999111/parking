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

        int remainCoupon = storeService.applyStoreDiscount(storeId, carNo, discountMinutes);

        return ResponseEntity.ok("할인 적용 완료 (남은 쿠폰: " + remainCoupon + "개)");
    }
}
