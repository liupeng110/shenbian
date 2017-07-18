//
//  RecommentCardCell.h
//  Miaohi
//
//  Created by 杨绍智 on 16/12/2.
//  Copyright © 2016年 haiqiu. All rights reserved.
//

#import <UIKit/UIKit.h>
//@protocol RecommentCardCelldelegate <NSObject>
//- (void)deleteCollectItem:(NSIndexPath*)index;
//- (void)RecomeCardattention:(SLButton*)btn andIndex:(NSIndexPath*)index;
//- (void)RecomeCardEnterPerPage:(NSIndexPath*)index;
//@end
@interface RecommentCardCell : UICollectionViewCell
//@property (nonatomic,weak)id<RecommentCardCelldelegate>delegate;
@property (nonatomic,strong)UIImageView * headImage;
@property (nonatomic,strong)UIView * cellView;
@property (nonatomic,strong)UILabel * nameLable;
@property (nonatomic,strong)UIView * noteView;
@property (nonatomic,strong)UILabel * perNote;
//-(void)setRecommentCardCell:(NSIndexPath*)indepath andModel:(MhSuqareViewRecomment*)model andDelete:(NSString*)btnState;
@end
