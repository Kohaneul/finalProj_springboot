package com.deli.project.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPickUp is a Querydsl query type for PickUp
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPickUp extends EntityPathBase<PickUp> {

    private static final long serialVersionUID = -62905291L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPickUp pickUp = new QPickUp("pickUp");

    public final QAddress address;

    public final ListPath<Category, QCategory> category = this.<Category, QCategory>createList("category", Category.class, QCategory.class, PathInits.DIRECT2);

    public final QCoordinate coordinate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isShow = createBoolean("isShow");

    public final QMember member;

    public final StringPath placeName = createString("placeName");

    public QPickUp(String variable) {
        this(PickUp.class, forVariable(variable), INITS);
    }

    public QPickUp(Path<? extends PickUp> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPickUp(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPickUp(PathMetadata metadata, PathInits inits) {
        this(PickUp.class, metadata, inits);
    }

    public QPickUp(Class<? extends PickUp> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.coordinate = inits.isInitialized("coordinate") ? new QCoordinate(forProperty("coordinate")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

