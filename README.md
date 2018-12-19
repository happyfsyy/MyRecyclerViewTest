# MyRecyclerViewTest
### NormalRecyclerViewActivity：
* 实现了基本的RecyclerView，加入了OnItemClickListener和OnClickListener(点击图片)，又加入了不同的ViewType，测试了adapter.notify。
* 为了测试RecyclerView的回收利用机制，先给onCreateViewHolder()加log，看执行了几次，然后在Adapter中加入了normalViewNum和headerViewNum，又在Activity中根据view.getTag，判断当前的itemView是不是新建的。

### MultiLayoutActivity:
* 实现了横向滑动的RecyclerView和StaggerGrid，为RecyclerView加入了divider。
* ListDecoration根据构造函数中传入的orientation来判断实现不同方向的divider。
