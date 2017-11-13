//
//  CHFindServiceOptimizedView.h
//  shenbianapp
//
//  Created by book on 2017/11/4.
//  Copyright © 2017 helinkeji. All rights reserved.
//

typedef void(^CHOptimizedSelected)(NSString *serviceId);

#import <UIKit/UIKit.h>

@interface CHFindServiceOptimizedView : UIView
@property(nonatomic,copy)NSArray *optimizedItemList;
@property(nonatomic,copy)CHOptimizedSelected optimizedSelected;
@end
