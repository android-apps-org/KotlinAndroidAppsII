# [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView)

## [Adapter](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter)

- Adapts list into an interface that RecyclerView can use
- RecyclerView uses this to draw list of objects
- RecyclerView uses onCreateViewHolder and onBindViewHolder to get ViewHolder
  - to bind views to specific item
- NotifyDataSetChanged: tells RecyclerView that entire list is potentially invalid
  - RecyclerView has to re-bind and re-draw every item in list
  - even for item that has been updated and is not even on screen now
  - causes a lot of screen updates
    - can appear as flicker to user or hiccup in scrolling

## [DiffUtil](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/DiffUtil)

- Help RecyclerView Adapter to calculate changes in list and minimize modifications
- Takes old list and new list and compare differences
- Finds items that were added, removed, or updated
- Uses Myers diff algorithm for minimum number of changes to make from old list to new list

## [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)

- Keeps track of list
- Takes callback for DiffUtil

## [ViewHolder](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.ViewHolder)

- Holds reference to views needed for display
- RecyclerView uses this to keep track of:
  - last position that views were drawn and scrolling
- Implementation detail of RecyclerView
  - provides convenient place for RecyclerView
  - to keep extra information about item
  - and where its being drawn on screen

## Resources

- [Create dynamic lists with RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Myers diff algorithm](https://blog.jcoglan.com/2017/02/12/the-myers-diff-algorithm-part-1/)
- [Binding Adapters](https://developer.android.com/topic/libraries/data-binding/binding-adapters)

