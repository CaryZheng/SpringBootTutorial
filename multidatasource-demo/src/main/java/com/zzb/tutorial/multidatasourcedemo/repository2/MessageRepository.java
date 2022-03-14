package com.zzb.tutorial.multidatasourcedemo.repository2;

import com.zzb.tutorial.multidatasourcedemo.entity2.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {


}
