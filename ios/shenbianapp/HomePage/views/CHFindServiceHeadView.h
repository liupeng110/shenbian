//
//  CHFindServiceHeadView.h
//  shenbianapp
//
//  Created by book on 2017/11/3.
//  Copyright © 2017 helinkeji. All rights reserved.
//

typedef void(^didSwitchCategory)(NSUInteger index);

#import <UIKit/UIKit.h>

@interface CHFindServiceHeadView : UIView
@property(nonatomic,copy)NSArray *categoryList;
@property(nonatomic,copy)didSwitchCategory didSwitchCategory;
@end
