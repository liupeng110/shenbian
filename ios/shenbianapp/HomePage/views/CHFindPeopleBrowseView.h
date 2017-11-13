//
//  CHFindPeopleBrowseView.h
//  shenbianapp
//
//  Created by book on 2017/11/4.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

typedef void(^CHScrollViewWillBeginDragging)();
typedef void(^CHDidSelectItem)(NSString *serviceId);

#import <UIKit/UIKit.h>

@interface CHFindPeopleBrowseView : UIView

@property(nonatomic,copy)NSArray *optimizedItemList;
@property(nonatomic,copy)CHScrollViewWillBeginDragging scrollViewWillBeginDragging;
@property(nonatomic,copy)CHDidSelectItem didSelectItem;
@end
