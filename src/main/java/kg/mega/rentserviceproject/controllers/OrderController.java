package kg.mega.rentserviceproject.controllers;

import kg.mega.rentserviceproject.dto.order.FormingOrderDTO;
import kg.mega.rentserviceproject.dto.order.GetOrderDTO;
import kg.mega.rentserviceproject.dto.order.SetDiscountDTO;
import kg.mega.rentserviceproject.models.order.Discount;
import kg.mega.rentserviceproject.services.order.CompleteOrderService;
import kg.mega.rentserviceproject.services.order.FormingOrderService;
import kg.mega.rentserviceproject.services.order.SetDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final CompleteOrderService completeOrderService;
    private final FormingOrderService formingOrderService;
    private final SetDiscountService setDiscountService;

    @PostMapping("forming-order")
    public GetOrderDTO formingOrder(@RequestBody FormingOrderDTO formingOrderDTO) {
        return formingOrderService.formingOrder(formingOrderDTO);
    }

    @PostMapping("set-discount")
    public Discount setDiscount(@RequestBody SetDiscountDTO discountDTO) {
        return setDiscountService.setDiscount(discountDTO);
    }

    @PatchMapping("complete-order")
    public ResponseEntity<String> completeOrder(@RequestParam Long orderId) {
        try {
            completeOrderService.completeOrder(orderId);
            return ResponseEntity.ok("Order completed.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error when completing an order: " + e.getMessage());
        }
    }
}