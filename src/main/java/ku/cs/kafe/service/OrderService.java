package ku.cs.kafe.service;

import ku.cs.kafe.common.Status;
import ku.cs.kafe.entity.Menu;
import ku.cs.kafe.entity.OrderItem;
import ku.cs.kafe.entity.OrderItemKey;
import ku.cs.kafe.entity.PurchaseOrder;
import ku.cs.kafe.model.AddCartRequest;
import ku.cs.kafe.repository.MenuRepository;
import ku.cs.kafe.repository.OrderItemRepository;
import ku.cs.kafe.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MenuRepository menuRepository;

    private UUID currentOrderId;

    public PurchaseOrder getCurrentOrder() {
        if (currentOrderId == null)
            createNewOrder();
        return purchaseOrderRepository.findById(currentOrderId).get();
    }

    public void submitOrder() {
        PurchaseOrder currentOrder =
                purchaseOrderRepository.findById(currentOrderId).get();
        currentOrder.setTimestamp(LocalDateTime.now());
        currentOrder.setStatus(Status.CONFIRM);
        purchaseOrderRepository.save(currentOrder);
        currentOrderId = null;
    }

    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    public void createNewOrder(){
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(Status.ORDER);
        PurchaseOrder record = purchaseOrderRepository.save(newOrder);
        currentOrderId = record.getId();
    }

    public PurchaseOrder getById(UUID id) {
        return purchaseOrderRepository.findById(id).get();
    }


    public void finishOrder(UUID orderId) {
        PurchaseOrder record = purchaseOrderRepository.findById(orderId).get();
        record.setStatus(Status.FINISH);
        purchaseOrderRepository.save(record);
    }


    public void order(UUID menuId, AddCartRequest request){
        if(currentOrderId == null){
            createNewOrder();
        }

        PurchaseOrder currentOrder = purchaseOrderRepository.findById(currentOrderId).get();

        Menu menu = menuRepository.findById(menuId).get();

        OrderItem item = new OrderItem();
        item.setId(new OrderItemKey(currentOrderId, menuId));
        item.setPurchaseOrder(currentOrder);
        item.setMenu(menu);
        item.setQuantity(request.getQuantity());

        orderItemRepository.save(item);
    }
}
// 6410451423 Siwakorn Pasawang