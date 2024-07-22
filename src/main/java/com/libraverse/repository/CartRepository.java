package com.libraverse.repository;


import com.libraverse.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query("select quantity from Cart where id = ?1")
    int findQuantityById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Cart SET quantity = ?2 WHERE id = ?1")
    void updateQuantityById(Long id, int res);


}

