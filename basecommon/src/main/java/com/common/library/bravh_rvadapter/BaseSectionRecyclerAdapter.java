package com.common.library.bravh_rvadapter;

import android.view.ViewGroup;

import com.common.library.bravh_rvadapter.entity.SectionEntity;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public abstract class BaseSectionRecyclerAdapter<T extends SectionEntity, K extends RecyclerViewHolder> extends BaseRecyclerAdapter<T, K> {


    protected int mSectionHeadResId;
    protected static final int SECTION_HEADER_VIEW = 0x00000444;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout_game_detail_outs_more_image_progress_bar id for each item
     * @param layoutResId      The layout_game_detail_outs_more_image_progress_bar resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public BaseSectionRecyclerAdapter(int layoutResId, int sectionHeadResId, List<T> data) {
        super(layoutResId, data);
        this.mSectionHeadResId = sectionHeadResId;
    }

    @Override
    protected int getDefItemViewType(int position) {
        return mData.get(position).isHeader ? SECTION_HEADER_VIEW : 0;
    }

    @Override
    protected K onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_HEADER_VIEW)
            return createBaseViewHolder(getItemView(mSectionHeadResId, parent));

        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected boolean isFixedViewType(int type) {
        return super.isFixedViewType(type) || type == SECTION_HEADER_VIEW;
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        switch (holder.getItemViewType()) {
            case SECTION_HEADER_VIEW:
                setFullSpan(holder);
                convertHead(holder, getItem(position - getHeaderLayoutCount()));
                break;
            default:
                super.onBindViewHolder(holder, position);
                break;
        }
    }

    protected abstract void convertHead(K holder, T item);

}
