package com.deli.project.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCoordinate is a Querydsl query type for Coordinate
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCoordinate extends BeanPath<Coordinate> {

    private static final long serialVersionUID = 1453072017L;

    public static final QCoordinate coordinate = new QCoordinate("coordinate");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public QCoordinate(String variable) {
        super(Coordinate.class, forVariable(variable));
    }

    public QCoordinate(Path<? extends Coordinate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCoordinate(PathMetadata metadata) {
        super(Coordinate.class, metadata);
    }

}

