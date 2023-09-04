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

    public void createNewOrder(){
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(Status.ORDER);
        PurchaseOrder record = purchaseOrderRepository.save(newOrder);
        currentOrderId = record.getId();
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
