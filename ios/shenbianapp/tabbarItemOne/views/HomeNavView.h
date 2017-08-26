//
//  HomeNavView.h
//  Miaohi
//
//  Created by 杨绍智 on 17/5/8.
//  Copyright © 2017年 haiqiu. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol HomeNavViewdelegate<NSObject>
- (void)HomeNavViewClickIndex:(NSInteger)index;
@end
@interface HomeNavView : UIView
@property (nonatomic,weak)id<HomeNavViewdelegate>delegate;


@end
