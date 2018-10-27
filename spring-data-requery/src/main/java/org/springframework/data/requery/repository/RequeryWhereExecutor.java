/*
 * Copyright 2018 Coupang Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.requery.repository;

import io.requery.query.Condition;
import io.requery.query.Result;
import io.requery.query.Return;
import io.requery.query.WhereAndOr;
import io.requery.query.element.QueryElement;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Requery {@link WhereAndOr} 를 이용하여 조회를 수행하는 메소드를 제공하는 Executor 입니다.
 *
 * @author debop
 * @since 18. 6. 20
 */
@NoRepositoryBean
public interface RequeryWhereExecutor<T> {

    @NotNull
    Optional<T> findOne(@NotNull final Return<? extends Result<T>> whereClause);

    @NotNull
    List<T> findAll(@NotNull final Return<? extends Result<T>> whereClause);

    @NotNull
    Page<T> findAll(@NotNull final QueryElement<? extends Result<T>> whereClause, @NotNull final Pageable pageable);

    @NotNull
    List<T> findAll(@NotNull final Iterable<Condition<T, ?>> conditions, @NotNull final Sort sort);

    @NotNull
    List<T> findAll(@NotNull final Iterable<Condition<T, ?>> conditions);

    /**
     * 해당 Where 조건에 해당하는 엔티티의 수
     *
     * @param whereClause Where 조건절
     * @return 조건절에 해당하는 엔티티 수
     */
    int count(@NotNull final QueryElement<? extends Result<T>> whereClause);

    /**
     * 해당 where 조건에 해당하는 엔티티가 존재하는지 여부
     *
     * @param whereClause Where 조건절
     * @return 조건절에 해당하는 엔티티 존재 여부
     */
    boolean exists(@NotNull final QueryElement<? extends Result<T>> whereClause);
}
