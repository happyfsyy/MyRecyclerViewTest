# MyRecyclerViewTest
#### 针对RecyclerView的各种测试：
---
* NormalDayAdapter测试RecyclerView的recycle机制，先给onCreateViewHolder()加log，看执行了几次。
* 再次，在Adatper中加入了一个normalViewNum，然后在Activity中根据view.getTag，判断当前的itemView是不是新建的。
* 再加入一个headerViewNum，测试不同ViewType的recycle
