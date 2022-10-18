package com.deli.project.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderCheck is a Querydsl query type for OrderCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderCheck extends EntityPathBase<OrderCheck> {

    private static final long serialVersionUID = 1984329587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderCheck orderCheck = new QOrderCheck("orderCheck");

    public final QBoard board;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginId = createString("loginId");

    public final EnumPath<OrderStatus> orderStatus = createEnum("orderStatus", OrderStatus.class);

    public final QRestaurant restaurant;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QOrderCheck(String variable) {
        this(OrderCheck.class, forVariable(variable), INITS);
    }

    public QOrderCheck(Path<? extends OrderCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderCheck(PathMetadata metadata, PathInits inits) {
        this(OrderCheck.class, metadata, inits);
    }

    public QOrderCheck(Class<? extends OrderCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

