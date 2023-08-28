package kitchenpos.fixture;

import kitchenpos.domain.Menu;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderStatus;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.OrderType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static kitchenpos.fixture.MenuFixture.generateMenu;
import static kitchenpos.fixture.OrderLineItemFixture.generateOrderLineItem;
import static kitchenpos.fixture.OrderTableFixture.generateOrderTable;

public class OrderFixture {

    private OrderFixture() {}

    private static final String DEFAULT_ADDRESS = "address";
    private static final int DEFAULT_QUANTITY = 1;

    public static Order initializeDeliveryOrder(final Menu menu, final long quantity) {
        return generateDeliveryOrder(OrderStatus.WAITING, menu, quantity, DEFAULT_ADDRESS);
    }

    public static Order initializeDeliveryOrder(final Menu menu, final long quantity, final String address) {
        return generateDeliveryOrder(OrderStatus.WAITING, menu, quantity, address);
    }

    public static Order generateDeliveryOrder(
            final OrderStatus orderStatus,
            final Menu menu,
            final long quantity,
            final String address
    ) {
        return createOrder(
                UUID.randomUUID(),
                OrderType.DELIVERY,
                orderStatus,
                List.of(generateOrderLineItem(menu, quantity)),
                address != null ? address : DEFAULT_ADDRESS,
                null
        );
    }

    public static Order initializeTakeOutOrder(final Menu menu, final long quantity) {
        return generateTakeoutOrder(OrderStatus.WAITING, menu, quantity);
    }

    public static Order generateTakeoutOrder(final OrderStatus orderStatus, final Menu menu, final long quantity) {
        return createOrder(
                UUID.randomUUID(),
                OrderType.TAKEOUT,
                orderStatus,
                List.of(generateOrderLineItem(menu, quantity)),
                null,
                null
        );
    }

    public static Order initializeEatInOrder(final Menu menu, final long quantity, final OrderTable orderTable) {
        return generateEatInOrder(OrderStatus.WAITING, menu, quantity, orderTable);
    }

    public static Order generateEatInOrder(
            final OrderStatus orderStatus,
            final Menu menu,
            final long quantity,
            final OrderTable orderTable
    ) {
        return createOrder(
                UUID.randomUUID(),
                OrderType.EAT_IN,
                orderStatus,
                List.of(generateOrderLineItem(menu, quantity)),
                null,
                orderTable
        );
    }

    public static Order automaticallyInitializeOrderByType(final OrderType orderType) {
        final Menu menu = generateMenu();

        switch (orderType) {
            case DELIVERY:
                return initializeDeliveryOrder(menu, DEFAULT_QUANTITY);
            case TAKEOUT:
                return initializeTakeOutOrder(menu, DEFAULT_QUANTITY);
            case EAT_IN:
                final OrderTable orderTable = generateOrderTable();
                return initializeEatInOrder(menu, DEFAULT_QUANTITY, orderTable);
            default:
                throw new IllegalStateException("Undefined behavior for " + orderType.name());
        }
    }

    public static Order automaticallyInitializeOrder(final OrderType orderType, final OrderStatus orderStatus) {
        final Menu menu = generateMenu();

        switch (orderType) {
            case DELIVERY:
                switch (orderStatus) {
                    case WAITING:
                        return initializeDeliveryOrder(menu, DEFAULT_QUANTITY);
                    case ACCEPTED:
                    case SERVED:
                    case DELIVERING:
                    case DELIVERED:
                    case COMPLETED:
                        return generateDeliveryOrder(orderStatus, menu, DEFAULT_QUANTITY, null);
                    default:
                        throw new IllegalStateException("Undefined behavior for " + orderStatus.name());
                }
            case TAKEOUT:
                switch (orderStatus) {
                    case WAITING:
                        return initializeTakeOutOrder(menu, DEFAULT_QUANTITY);
                    case ACCEPTED:
                    case SERVED:
                    case COMPLETED:
                        return generateTakeoutOrder(orderStatus, menu, DEFAULT_QUANTITY);
                    default:
                        throw new IllegalStateException("Undefined behavior for " + orderStatus.name());
                }
            case EAT_IN:
                final OrderTable orderTable = generateOrderTable();
                switch (orderStatus) {
                    case WAITING:
                        return initializeEatInOrder(menu, DEFAULT_QUANTITY, orderTable);
                    case ACCEPTED:
                    case SERVED:
                    case COMPLETED:
                        return generateEatInOrder(orderStatus, menu, DEFAULT_QUANTITY, orderTable);
                    default:
                        throw new IllegalStateException("Undefined behavior for " + orderStatus.name());
                }
            default:
                throw new IllegalStateException("Undefined behavior for " + orderType.name());
        }
    }

    private static Order createOrder(
            final UUID id,
            final OrderType type,
            final OrderStatus status,
            final List<OrderLineItem> orderLineItems,
            final String deliveryAddress,
            final OrderTable orderTable
    ) {
        Order order = new Order();

        order.setId(id);
        order.setType(type);
        order.setStatus(status);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderLineItems(orderLineItems);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderTable(orderTable);

        if (orderTable != null) {
            order.setOrderTableId(orderTable.getId());
        }

        return order;
    }
}
